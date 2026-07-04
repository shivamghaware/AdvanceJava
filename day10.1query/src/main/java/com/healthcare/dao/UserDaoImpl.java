package com.healthcare.dao;

import com.healthcare.dtos.UserDTO;
import com.healthcare.entities.User;
import com.healthcare.entities.UserRole;

import org.hibernate.*;
import static com.healthcare.utils.HibernateUtils.getFactory;

import java.time.LocalDate;
import java.util.List;

public class UserDaoImpl implements UserDao {

	@Override
	public String registerUser(User user) {
		// user - TRANSIENT
		String mesg = "User reg failed !!!!";
		/*
		 * 1. Get Session from SessionFactory SessionFactory Methods 1.1 public Session
		 * openSession() throws HibernateException OR 1.2 public Session
		 * getCurrentSession() throws HibernateException
		 * 
		 */
		Session session = getFactory().getCurrentSession();// new
		Session session2 = getFactory().getCurrentSession();// existing
		System.out.println(session == session2);// true
		/*
		 * 2. Begin a Transaction(tx) Session API public Transaction beginTransaction()
		 * throws HibernateException
		 */
		Transaction tx = session.beginTransaction();
		System.out.println("is connected " + session.isConnected() + " is open " + session.isOpen());// t t
		try {
			/*
			 * Session API to add new record public void persist(Object transientObj) throws
			 * HibernateException
			 */
			session.persist(user);// user - PERSISTENT (part of L1 cache , not in DB)
			tx.commit();
			/*
			 * 1. session.flush() -> Dirty checking -> DML : insert 2. session.close() -> L1
			 * cache is destroyed , DB cn rets to DBCP session obj destroyed
			 */

			mesg = "User registered succcessfully , with ID=" + user.getUserId();
			System.out.println("is connected " + session.isConnected() + " is open " + session.isOpen());// f f
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
				System.out.println("is connected " + session.isConnected() + " is open " + session.isOpen());// f f
				// re throw the same exception to the caller
				throw e;
			}
		}
		// user - DETACHED (from L1 cache)
		return mesg;
	}

	@Override
	public User getUserDetails(Long userId) {
		User user = null;// user : doesn't exist in heap
		// 1. Get Session from SessionFactory
		Session session = getFactory().getCurrentSession();
		// 2. Begin Tx
		Transaction tx = session.beginTransaction();
		// 3 try-catch
		try {
			// 4. get user details
			user = session.find(User.class, userId);// select
//		user=session.find(User.class, userId);
//			user=session.find(User.class, userId);
			// in case of valid id , user - PERSISTENT(exists in L1 cache , as well as DB)
			// invalid id - null
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
				// re throw the same exception to the caller
				throw e;
			}
		}

		return user;// valid id , user - DETACHED
	}

	@Override
	public List<User> getAllUserDetails() {
		List<User> users = null;
		String jpql = "select u from User u";
		// 1. Get Session from SessionFactory
		Session session = getFactory().getCurrentSession();
		// 2. Begin Tx
		Transaction tx = session.beginTransaction();
		// 3 try-catch
		try {
			users = session.createQuery(jpql, User.class) // Query
					.getResultList(); // exec -> RST -> List<User>
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
				// re throw the same exception to the caller
				throw e;
			}
		}
		return users;
	}

	@Override
	public List<User> getUsersByRoleAndDate(UserRole role1, LocalDate dob1) {
		List<User> users = null;
		String jpql = "select u from User u where u.role=:rl and u.dob>:dt";
		// 1. Get Session from SessionFactory
		Session session = getFactory().getCurrentSession();
		// 2. Begin Tx
		Transaction tx = session.beginTransaction();
		// 3 try-catch
		try {
			users = session.createQuery(jpql, User.class).setParameter("rl", role1).setParameter("dt", dob1)
					.getResultList();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
				// re throw the same exception to the caller
				throw e;
			}
		}
		return users;
	}

	@Override
	public List<String> getLastNamesByRole(UserRole role) {
		List<String> lastNames = null;
		String jpql = "select u.lastName from User u where u.role=:rl";
		// 1. Get Session from SessionFactory
		Session session = getFactory().getCurrentSession();
		// 2. Begin Tx
		Transaction tx = session.beginTransaction();
		// 3 try-catch
		try {
			lastNames = session.createQuery(jpql, String.class).setParameter("rl", role).getResultList();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
				// re throw the same exception to the caller
				throw e;
			}
		}
		return lastNames;
	}

	@Override
	public List<UserDTO> getSelectedDetailsByRole(UserRole role) {
		List<UserDTO> users = null;
		String jpql = "select new com.healthcare.dtos.UserDTO(u.firstName,u.lastName,u.dob) from User u where u.role=:rl";
		// 1. Get Session from SessionFactory
		Session session = getFactory().getCurrentSession();
		// 2. Begin Tx
		Transaction tx = session.beginTransaction();
		// 3 try-catch
		try {
			users = session.createQuery(jpql, UserDTO.class).setParameter("rl", role).getResultList();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
				// re throw the same exception to the caller
				throw e;
			}
		}
		return users;
	}

	@Override
	public User authenticate(String email1, String password1) {
		String jpql = "select u from User u where u.email=:em and u.password=:pass";
		User user = null;
		// 1. Get Session from SessionFactory
		Session session = getFactory().getCurrentSession();
		// 2. Begin Tx
		Transaction tx = session.beginTransaction();
		// 3 try-catch
		try {
			user = session.createQuery(jpql, User.class).setParameter("em", email1).setParameter("pass", password1)
					.getSingleResult();
			// valid login - user : PERSISTENT
			// invalid login - throws NoResultException
			// modifying the state of persistent entity
			user.setRegAmount(user.getRegAmount() + 100);
			user.setPassword("55555");
			// user : Detached
			tx.commit();
			session.evict(user);
			/*
			 * session.flush() -> updated state of the entity -> DML - update session.close
			 * -> L1 cache destroyed , db cn rets to DBCP
			 */
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
				// re throw the same exception to the caller
				throw e;
			}
		}
		/*
		 * modifying state of the detached entity -> hibernate can't track the changes
		 * in the state
		 */
		user.setRegAmount(user.getRegAmount() + 100);
		return user;// user - DETACHED
	}

	@Override
	public String applyDiscount(UserRole role, int discount) {
		String mesg="Updation failed....";
		String jpql="update User u set u.regAmount=u.regAmount-:disc where u.role=:rl";
		// 1. Get Session from SessionFactory
		Session session = getFactory().getCurrentSession();
		// 2. Begin Tx
		Transaction tx = session.beginTransaction();
		// 3 try-catch
		try {
			int updateCount=session.createMutationQuery(jpql)
			.setParameter("disc", discount)
			.setParameter("rl", role)
			.executeUpdate();
			tx.commit();
			mesg=updateCount+" number of users are given discount !";
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
				// re throw the same exception to the caller
				throw e;
			}
		}
		return mesg;
	}

	@Override
	public String deleteUserDetails(Long userId) {
		String mesg="Deletion failed !!!!!!!!";
		User user=null;
		// 1. Get Session from SessionFactory 
		Session session=getFactory().getCurrentSession();
		//2. Begin Tx
		Transaction tx=session.beginTransaction();
		//3 try-catch
		try {
			//4. find - get entity by its id
			user=session.find(User.class, userId);//select
			if(user != null)
			{
				//user : persistent
				session.remove(user);
				//user - REMOVED (exists in L1 persistence ctx + db)
				mesg ="user details will be deleted upon commit";
			}
			tx.commit();
			/*
			 * session.flush() -> DML - delete
			 * session.close() -> L1 cache destroyed , db cn rets to DBCP
			 */
		} catch (RuntimeException e) {
			if(tx != null)
			{
				tx.rollback();
				//re throw the same exception to the caller
				throw e;
			}
		}
		//user - Transient
		return mesg;
	}
	//user - marked for GC - Does not exist !
	
	

}
