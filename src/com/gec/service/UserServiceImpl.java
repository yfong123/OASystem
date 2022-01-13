package com.gec.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gec.domain.Expense;
import com.gec.domain.ExpenseExample;
import com.gec.domain.Leave;
import com.gec.domain.LeaveExample;
import com.gec.domain.PageBean;
import com.gec.domain.Role;
import com.gec.domain.User;
import com.gec.mapper.DeptMapper;
import com.gec.mapper.ExpenseMapper;
import com.gec.mapper.LeaveMapper;
import com.gec.mapper.RoleMapper;
import com.gec.mapper.UserMapper;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private DeptMapper deptMapper;
	@Autowired
	private LeaveMapper leaveMapper;
	@Autowired
	private ExpenseMapper expenseMapper;
	
	
	@Override
	public PageBean<User> getUserList(
			Integer page, Integer limit, Map<String, Object> data) {
		//计算偏移记录数。
		int offset = (page-1)*limit; 	
		List<User> list = null;
		list = userMapper.queryUserList(offset, limit,data);
		int count = userMapper.queryUserCount(data);
		PageBean<User> pBean = new PageBean<User>();
		pBean.setCount( count );
		pBean.setList( list );		
		return pBean;
	}

	@Override
	public void saveUser(User user,String roleId) {
		if (user.getUsername()==""||user.getPassword()==""||user.getNo()==""||user.getDeptid()==null||roleId==null) {
			throw new NullPointerException("用户信息不全"); 
		}
		String deptId = user.getDeptid();
		Role role = roleMapper.selectByPrimaryKey(roleId);
		String rolename = role.getRolename();
		
		String bossId = roleMapper.findBossId();
		if (rolename.equals("boss")&&bossId!=null) {
			throw new RuntimeException("总经理已存在"); 
		}
		String leaderId = deptMapper.findDeptLeaderId(deptId);
		if (rolename.equals("leader")&&leaderId!=null) {
			throw new RuntimeException("该部门主管已存在");
		}
		String managerId = deptMapper.findDeptManagerId(deptId);
		if (rolename.equals("manager")&&managerId!=null) {
			throw new RuntimeException("该部门经理已存在");
		}
		
		if (user.getId()!=null) {
			int updateCnt = userMapper.updateByPrimaryKeySelective(user);
			if (updateCnt!=1) {
				throw new RuntimeException("更新用户失败");
			}
			
			int cnt = userMapper.updateUserRole(user.getId(),roleId);
			if (cnt!=1) {
				throw new RuntimeException("更新用户角色失败");
			}
			
		}else {
			String maxId = userMapper.queryMaxId();
			if (maxId==null) {
				user.setId("u01");
			}else {
				String id = maxId.split("u")[1];
				int uid = Integer.valueOf(id);
				if (uid>=1&&uid<9) {
					user.setId("u0"+(uid+1));
				}else {
					user.setId("u"+(uid+1));
				}
			}
			
			
			User gUser = userMapper.queryByNo(user.getNo());
			if (gUser!=null) {
				throw new RuntimeException("工号已存在");
			}
			user.setCreatedate(new Date());
			int insertCnt = userMapper.insertSelective(user);
			if (insertCnt!=1) {
				throw new RuntimeException("添加用户失败");
			}
			int cnt = userMapper.insertUserRole(user.getId(),roleId);
			if (cnt!=1) {
				throw new RuntimeException("添加用户角色失败");
			}
		}
	}

	@Override
	public User getUserById(String id) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id", id);
		User user = null;
		user = userMapper.queryUserList(0, 1, data).get(0);
		System.out.println(user);
		if (user==null) {
			throw new RuntimeException("查不到该用户信息");
		}
		return user;
	}

	@Override
	public void delUser(String id) {
		LeaveExample leaveExample = new LeaveExample();
		leaveExample.createCriteria().andAssigneeEqualTo(id);
		List<Leave> leaves = leaveMapper.selectByExample(leaveExample);
		if (leaves.size()!=0) {
			throw new RuntimeException("该用户还有办理中的请假任务");
		}
		ExpenseExample exExample = new ExpenseExample();
		exExample.createCriteria().andAssigneeEqualTo(id);
		List<Expense> expenses = expenseMapper.selectByExample(exExample);
		if (expenses.size()!=0) {
			throw new RuntimeException("该用户还有办理中的报销任务");
		}
		int delCnt = userMapper.deleteByPrimaryKey(id);
		if (delCnt!=1) {
			throw new RuntimeException("删除用户失败");
		}
		//删除用户的角色信息
		int cnt = userMapper.deleteUserRole(id);
		if (cnt!=1) {
			throw new RuntimeException("删除用户角色失败");
		}
	}
}
