package com.nan.ia.app.biz;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;

import com.nan.ia.app.App;
import com.nan.ia.app.constant.Constant;
import com.nan.ia.app.data.AppData;
import com.nan.ia.app.data.ResourceMapper;
import com.nan.ia.app.db.DBService;
import com.nan.ia.app.entities.AccountInfo;
import com.nan.ia.app.http.cmd.server.AccountLoginServerCmd;
import com.nan.ia.app.http.cmd.server.RegisterServerCmd;
import com.nan.ia.app.http.cmd.server.SyncDataServerCmd;
import com.nan.ia.app.http.cmd.server.VerifyMailServerCmd;
import com.nan.ia.app.http.cmd.server.VerifyVfCodeServerCmd;
import com.nan.ia.common.constant.ServerErrorCode;
import com.nan.ia.common.entities.AccountBook;
import com.nan.ia.common.entities.AccountBookDelete;
import com.nan.ia.common.entities.AccountCategory;
import com.nan.ia.common.entities.AccountCategoryDelete;
import com.nan.ia.common.entities.AccountRecord;
import com.nan.ia.common.entities.AccountRecordDelete;
import com.nan.ia.common.http.cmd.entities.AccountLoginRequestData;
import com.nan.ia.common.http.cmd.entities.AccountLoginResponseData;
import com.nan.ia.common.http.cmd.entities.RegisterRequestData;
import com.nan.ia.common.http.cmd.entities.ServerResponse;
import com.nan.ia.common.http.cmd.entities.SyncDataRequestData;
import com.nan.ia.common.http.cmd.entities.SyncDataResponseData;
import com.nan.ia.common.http.cmd.entities.VerifyMailRequestData;
import com.nan.ia.common.http.cmd.entities.VerifyVfCodeRequestData;

public class BizFacade {

	private static BizFacade sInstance = null;
	
	public static BizFacade getInstance() {
		if (null != sInstance) {
			return sInstance;
		}
		
		synchronized (BizFacade.class) {
			if (null == sInstance) {
				sInstance = new BizFacade();
			}
		}
		
		return sInstance;
	}

	/**
	 * 初始化应用内容 
	 */
	public void appInit() {
		// 初始化数据
		AppData.initAppData(App.getInstance());
		
		if (AppData.getAccountInfo() == null) {
			// 没有账户信息，创建本地默认账户
			createDefaultAccountInfo();
		}
		
		if (AppData.getAccountBooks().size() == 0) {
			// 没有账本，创建默认账本，并设置为当前账本
			AccountBook accountBook = createDefaultAccountBook();
			AppData.setCurrentAccountBookId(accountBook.getAccountBookId());
		}
		
		// 设置当前账本
		boolean hasCurrentAccountBook = false;
		for (int i = 0; i < AppData.getAccountBooks().size(); i++) {
			if (AppData.getAccountBooks().get(i).getAccountBookId() == AppData.getCurrentAccountBookId()) {
				// 已经有当前账本了
				hasCurrentAccountBook = true;
				break;
			}
		}
		
		if (!hasCurrentAccountBook) {
			// 没有当前账本，则设置第一个账本
			AppData.setCurrentAccountBookId(AppData.getAccountBooks().get(0).getAccountBookId());
		}
	}
	
	private void createDefaultAccountInfo() {
		AccountInfo accountInfo = new AccountInfo();
		accountInfo.setAccountType(Constant.ACCOUNT_TYPE_UNLOGIN);
		accountInfo.setUserId(Constant.UNLOGIN_USER_ID);
		AppData.setAccountInfo(accountInfo);
	}
	
	/**
	 * 创建默认账本
	 */
	private AccountBook createDefaultAccountBook() {
		return createAccountBook("生活账本（默认）", "记录生活中的点滴");
	}
	
	/**
	 * 给账本创建默认分类
	 * @param accountBookId
	 */
	private void createDefaultCategory(int accountBookId) {
		// 开始存储
		AppData.beginStore();
		
		createCategory(accountBookId, "", "支出", ResourceMapper.icon_category_45);
		createCategory(accountBookId, "支出", "餐饮", ResourceMapper.icon_category_32);
		createCategory(accountBookId, "支出", "交通", ResourceMapper.icon_category_2);
		createCategory(accountBookId, "支出", "购物", ResourceMapper.icon_category_34);
		createCategory(accountBookId, "支出", "水果零食", ResourceMapper.icon_category_41);
		createCategory(accountBookId, "支出", "酒水饮料", ResourceMapper.icon_category_44);
		createCategory(accountBookId, "支出", "居家", ResourceMapper.icon_category_37);
		createCategory(accountBookId, "支出", "手机通讯", ResourceMapper.icon_category_40);
		createCategory(accountBookId, "支出", "报销账", ResourceMapper.icon_category_31);
		createCategory(accountBookId, "支出", "借出", ResourceMapper.icon_category_36);
		createCategory(accountBookId, "支出", "娱乐", ResourceMapper.icon_category_47);
		createCategory(accountBookId, "支出", "淘宝", ResourceMapper.icon_category_43);
		createCategory(accountBookId, "支出", "人情礼物", ResourceMapper.icon_category_39);
		createCategory(accountBookId, "支出", "医疗教育", ResourceMapper.icon_category_46);
		createCategory(accountBookId, "支出", "书籍", ResourceMapper.icon_category_42);
		createCategory(accountBookId, "支出", "美容运动", ResourceMapper.icon_category_38);
		createCategory(accountBookId, "支出", "宠物", ResourceMapper.icon_category_33);
		
		createCategory(accountBookId, "", "收入", ResourceMapper.icon_category_21);
		createCategory(accountBookId, "收入", "工资", ResourceMapper.icon_category_21);
		createCategory(accountBookId, "收入", "奖金", ResourceMapper.icon_category_23);
		createCategory(accountBookId, "收入", "外快兼职", ResourceMapper.icon_category_24);
		createCategory(accountBookId, "收入", "借入", ResourceMapper.icon_category_25);
		createCategory(accountBookId, "收入", "投资收入", ResourceMapper.icon_category_29);
		createCategory(accountBookId, "收入", "红包", ResourceMapper.icon_category_22);
		createCategory(accountBookId, "收入", "零花钱", ResourceMapper.icon_category_26);
		createCategory(accountBookId, "收入", "生活费", ResourceMapper.icon_category_28);
		createCategory(accountBookId, "收入", "其他", ResourceMapper.icon_category_27);
		
		AppData.endStore();
	}
	
	// 账本相关接口
	public AccountBook getCurrentAccountBook() {
		List<AccountBook> accountBooks = AppData.getAccountBooks();
		for (int i = 0; i < accountBooks.size(); i++) {
			if (accountBooks.get(i).getAccountBookId() == AppData.getCurrentAccountBookId()) {
				return accountBooks.get(i);
			}
		}
		
		return null;
	}
	
	public void setCurrentAccountBook(int accountBookId) {
		AppData.setCurrentAccountBookId(accountBookId);
	}
	
	public List<AccountBook> getAccountBooks() {
		return AppData.getAccountBooks();
	}
	
	public boolean checkDuplicationAccountBookName(String name) {
		List<AccountBook> accountBooks = AppData.getAccountBooks();
		for (int i = 0; i < accountBooks.size(); i++) {
			if (accountBooks.get(i).getName().equalsIgnoreCase(name)) {
				return true;
			}
		}
		
		return false;
	}
	
	public int allocateNewAccountBookId() {
		AppData.setCreateAccountBookId(AppData.getCreateAccountBookId() - 1);
		return AppData.getCreateAccountBookId() + 1;
	}
	
	public AccountBook createAccountBook(String name, String description) {
		AccountBook accountBook = new AccountBook();
		accountBook.setAccountBookId(allocateNewAccountBookId());
		accountBook.setCreateUserId(AppData.getAccountInfo().getUserId());
		accountBook.setName(name);
		accountBook.setDescription(description);
		
		accountBook.setCreateTime(new Date());
		accountBook.setUpdateTime(new Date());
		
		// 开始存储
		AppData.beginStore();
		
		// 添加账本数据
		AppData.getAccountBooks().add(accountBook);
		// 保存数据
		AppData.setAccountBooks(AppData.getAccountBooks());
		// 给该账本创建默认分类
		createDefaultCategory(accountBook.getAccountBookId());
		
		// 结束存储
		AppData.endStore();
		
		return accountBook;
	}
	
	public AccountBook getAccountBookById(int accountBookId) {
		List<AccountBook> accountBooks = AppData.getAccountBooks();
		for (int i = 0; i < accountBooks.size(); i++) {
			AccountBook accountBook = accountBooks.get(i);
			if (accountBookId == accountBooks.get(i).getAccountBookId()) {
				return accountBook;
			}
		}
		
		return null;
	}
	
	public AccountBook editAccountBooksDetail(int accountBookId, String name, String description) {
		AccountBook accountBook = getAccountBookById(accountBookId);
		if (null != accountBook) {
			accountBook.setName(name);
			accountBook.setDescription(description);
			accountBook.setUpdateTime(new Date());
			// 保存数据
			AppData.setAccountBooks(AppData.getAccountBooks());
		}
		
		return accountBook;
	}
	
	public void deleteAccountBook(int accountBookId) {
		ArrayList<AccountBook> accountBooks = AppData.getAccountBooks();
		for (int i = 0; i < accountBooks.size(); i++) {
			AccountBook accountBook = accountBooks.get(i);
			if (accountBookId == accountBooks.get(i).getAccountBookId()) {
				accountBooks.remove(accountBook);
				// 保存数据
				AppData.setAccountBooks(accountBooks);

				if (AppData.getLastSyncDataLocalTime() > accountBook.getCreateTime().getTime()) {
					// 是在最后一次同步之前创建的，则需要保留到delete中，以同步数据
					AccountBookDelete delete = new AccountBookDelete();
					delete.setAccountBookId(accountBook.getAccountBookId());
					delete.setDeleteUserId(AppData.getAccountInfo().getUserId());
					delete.setDeleteTime(new Date());
					
					// 保存数据
					AppData.getAccountBookDeletes().add(delete);
					AppData.setAccountBookDeletes(AppData.getAccountBookDeletes());
				}
				
				break;
			}
		}
	}
	
	// 类别相关接口
	public List<AccountCategory> getCategories() {
		return null;
	}
	
	public boolean checkDuplicationCategory(String category) {
		List<AccountCategory> accountCategories = AppData.getAccountCategories();
		for (int i = 0; i < accountCategories.size(); i++) {
			if (accountCategories.get(i).getCategory().equals(category)) {
				return true;
			}
		}
		
		return false;
	}
	
	public AccountCategory createCategory(int accountBookId, String superCategory, String category, String icon) {
		AccountCategory accountCategory = new AccountCategory();
		accountCategory.setAccountBookId(accountBookId);
		accountCategory.setCategory(category);
		accountCategory.setIcon(icon);
		accountCategory.setSuperCategory(superCategory);
		accountCategory.setCreateTime(new Date());
		accountCategory.setUpdateTime(new Date());
		
		// 添加分类数据
		AppData.getAccountCategories().add(accountCategory);
		// 保存数据
		AppData.setAccountCategories(AppData.getAccountCategories());
		
		return accountCategory;
	}
	
	public AccountCategory getCategory(int accountBookId, String category) {
		for (int i = 0; i < AppData.getAccountCategories().size(); i++) {
			AccountCategory accountCategory = AppData.getAccountCategories().get(i);
			if (accountBookId == accountCategory.getAccountBookId() && accountCategory.getCategory().equals(category)) {
				return AppData.getAccountCategories().get(i);
			}
		}
		
		return null;
	}
	
	public List<AccountCategory> getSubCategories(int accountBookId, String category) {
		List<AccountCategory> subAccountCategories = new ArrayList<AccountCategory>();
		for (int i = 0; i < AppData.getAccountCategories().size(); i++) {
			AccountCategory accountCategory = AppData.getAccountCategories().get(i);
			if (accountBookId == accountCategory.getAccountBookId() && accountCategory.getSuperCategory().equals(category)) {
				subAccountCategories.add(AppData.getAccountCategories().get(i));
			}
		}
		
		return subAccountCategories;
	}
	
	public AccountCategory getRootCategory(int accountBookId, String category) {
		AccountCategory accountCategory = getCategory(accountBookId, category);
		if (accountCategory.getSuperCategory() == null || accountCategory.getSuperCategory().isEmpty()) {
			return accountCategory;
		} else {
			return getRootCategory(accountBookId, accountCategory.getSuperCategory());
		}
	}

	public AccountCategory editCategory(int accountBookId, String category, String newCategory) {
		AccountCategory accountCategory = getCategory(accountBookId, category);
		if (null != accountCategory) {
			// 修改分类
			accountCategory.setCategory(newCategory);
						
			// 修改子分类的父分类
			for (int i = 0; i < AppData.getAccountCategories().size(); i++) {
				if (AppData.getAccountCategories().get(i).getSuperCategory().equals(category)) {
					AppData.getAccountCategories().get(i).setSuperCategory(newCategory);
				}
			}
			
			// 保存数据
			AppData.setAccountCategories(AppData.getAccountCategories());
		}
		
		return accountCategory;
	}
	
	public void deleteCategory(int accountBookId, String category) {
		AccountCategory accountCategory = getCategory(accountBookId,
				category);
		if (null != accountCategory) {
			return;
		}
		
		// 删除分类
		AppData.getAccountCategories().remove(accountCategory);

		// 删除子分类
		List<AccountCategory> deleteAccountCategories = getSubCategories(
				accountBookId, category);
		AppData.getAccountCategories().removeAll(deleteAccountCategories);

		// 添加到删除对象中
		deleteAccountCategories.add(accountCategory);
		Date now = new Date();
		for (int i = 0; i < deleteAccountCategories.size(); i++) {
			if (deleteAccountCategories.get(i).getCreateTime().getTime() > AppData
					.getLastSyncDataLocalTime()) {
				// 最后一次同步数据之后创建的分类，不需要保存到删除对象中
				continue;
			}

			AccountCategoryDelete delete = new AccountCategoryDelete();
			delete.setAccountBookId(deleteAccountCategories.get(i)
					.getAccountBookId());
			delete.setCategory(deleteAccountCategories.get(i).getCategory());
			delete.setDeleteUserId(AppData.getAccountInfo().getUserId());
			delete.setDeleteTime(now);

			AppData.getAccountCategoryDeletes().add(delete);
		}

		// 保存数据
		AppData.setAccountCategories(AppData.getAccountCategories());
		AppData.setAccountCategoryDeletes(AppData.getAccountCategoryDeletes());
	}
	
	// 账本条目相关接口
	public List<AccountRecord> getMoreAccountRecords(int accountBookId, long beginTime) {
		return DBService.getInstance(App.getInstance()).queryMoreAccountRecords(accountBookId, beginTime);
	}

	public void createAccountRecord(AccountRecord record) {
		DBService.getInstance(App.getInstance()).createAccountRecord(record);
	}

	public void editAccountRecord(AccountRecord record) {
		DBService.getInstance(App.getInstance()).updateAccountRecord(record);
	}
	
	public void deleteAccountRecord(int accountRecordId) {
		DBService.getInstance(App.getInstance()).deleteAccountRecord(accountRecordId);
	}
	
	// 重新加载数据
	public void reloadDataFromDB() {
		
	}
	
	/**
	 * 尝试同步数据
	 */
	public void trySyncDataToServer() {
		
	}
	
	// 同步服务器数据
	public void syncDataToServer() {
		DBService dbService = DBService.getInstance(App.getInstance());
		long lastSyncTime = AppData.getLastSyncDataLocalTime();
		
		// 获得需要同步的账本
		List<AccountBook> newBooks = new ArrayList<AccountBook>();					// 新建账本
		List<AccountBook> updateBooks = new ArrayList<AccountBook>();				// 更新账本
		List<AccountBookDelete> bookDeletes = AppData.getAccountBookDeletes();		// 删除账本
		for (int i = 0; i < AppData.getAccountBooks().size(); i++) {
			AccountBook accountBook = AppData.getAccountBooks().get(i);
			if (accountBook.getCreateTime().getTime() > lastSyncTime) {
				// 本地新建的账本
				newBooks.add(accountBook);
			} else if (accountBook.getUpdateTime().getTime() > lastSyncTime) {
				// 更新的装备
				updateBooks.add(accountBook);
			}
		}
		
		// 获得需要同步的类别数据
		List<AccountCategory> newCategories = new ArrayList<AccountCategory>();      		// 新建类别
		List<AccountCategory> updateCategories = new ArrayList<AccountCategory>();   		// 更新类别
		List<AccountCategoryDelete> categoryDeletes = AppData.getAccountCategoryDeletes(); 	// 删除的类别
		for (int i = 0; i < AppData.getAccountCategories().size(); i++) {
			AccountCategory category = AppData.getAccountCategories().get(i);
			if (category.getCreateTime().getTime() > lastSyncTime) {
				// 本地新建类别
				newCategories.add(category);
			} else if (category.getUpdateTime().getTime() > lastSyncTime) {
				// 本地更新类别
				updateCategories.add(category);
			}
		}
		
		// 获得需要同步的账本记录
		List<AccountRecord> newRecords = dbService.queryNewAccountRecords(lastSyncTime);	   	// 新建记录
		List<AccountRecord> updateRecords = dbService.queryUpdateAccountRecords(lastSyncTime); 	// 更新记录
		List<AccountRecordDelete> recordDeletes = AppData.getAccountRecordDeletes();			// 删除的记录
		
		SyncDataRequestData requestData = new SyncDataRequestData();
		requestData.setLastSyncDataTime(AppData.getLastSyncDataTime());
		requestData.setNewBooks(newBooks);
		requestData.setUpdateBooks(updateBooks);
		requestData.setBookDeletes(bookDeletes);
		
		requestData.setNewCategories(newCategories);
		requestData.setUpdateCategories(updateCategories);
		requestData.setCategoryDeletes(categoryDeletes);
		
		requestData.setNewRecords(newRecords);
		requestData.setUpdateRecords(updateRecords);
		requestData.setRecordDeletes(recordDeletes);
		
		SyncDataServerCmd cmd = new SyncDataServerCmd();
		ServerResponse<SyncDataResponseData> response = cmd.send(App.getInstance(), requestData, false);
	}
	
	// 用户相关接口
	
	
	/**
	 * 验证邮箱
	 * @param mail
	 */
	public ServerResponse<Object> verifyMail(Context context, String mail) {
		VerifyMailRequestData requestData = new VerifyMailRequestData();
		requestData.setMail(mail);
		return new VerifyMailServerCmd().send(context, requestData, false);
	}
	
	/**
	 * 验证验证码
	 * @param context
	 * @param mail
	 * @param vfCode
	 * @return
	 */
	public ServerResponse<Object> verifyVfCode(Context context, String username, int accountType, int vfCode) {
		VerifyVfCodeRequestData requestData = new VerifyVfCodeRequestData();
		requestData.setUsername(username);
		requestData.setAccountType(accountType);
		requestData.setVfCode(vfCode);
		return new VerifyVfCodeServerCmd().send(context, requestData, false);
	}
	
	/**
	 * 注册
	 * @param context
	 * @param username
	 * @param accountType
	 * @param vfCode
	 * @return
	 */
	public ServerResponse<Object> register(Context context, String username, String password, int accountType, int vfCode) {
		RegisterRequestData requestData = new RegisterRequestData();
		requestData.setUsername(username);
		requestData.setPassword(password);
		requestData.setAccountType(accountType);
		requestData.setVfCode(vfCode);
		return new RegisterServerCmd().send(context, requestData, false);
	}
	
	/**
	 * 账户登录
	 * @param context
	 * @param username
	 * @param password
	 * @param accountType
	 * @param vfCode
	 * @return
	 */
	public ServerResponse<AccountLoginResponseData> accountLogin(Context context, String username, String password) {
		AccountLoginRequestData requestData = new AccountLoginRequestData();
		requestData.setUsername(username);
		requestData.setPassword(password);
		requestData.setAccountType(Constant.ACCOUNT_TYPE_MAIL);
		
		ServerResponse<AccountLoginResponseData> response = new AccountLoginServerCmd().send(context, requestData, false);
		if (response.getRet() == ServerErrorCode.RET_SUCCESS) {
			doAfterLoginSuccess(response.getData());
		}
		
		return response;
	}
	
	private void doAfterLoginSuccess(AccountLoginResponseData data) {
		AccountInfo accountInfo = AppData.getAccountInfo();
		accountInfo.setAccountType(data.getAccountType());
		accountInfo.setUsername(data.getUsername());
		accountInfo.setUserId(data.getUserId());
		accountInfo.setToken(data.getToken());
		
		AppData.getUserInfoCache().put(data.getUserId(), data.getUserInfo());
		
		// 保存到文件中
		AppData.beginStore();
		AppData.setAccountInfo(accountInfo);
		AppData.setUserInfoCache(AppData.getUserInfoCache());
		AppData.endStore();
	}
}