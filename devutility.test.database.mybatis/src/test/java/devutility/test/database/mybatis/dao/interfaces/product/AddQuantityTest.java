package devutility.test.database.mybatis.dao.interfaces.product;

import devutility.internal.test.TestExecutor;
import devutility.test.database.mybatis.BaseTest;
import devutility.test.database.mybatis.dao.interfaces.ProductDao;

public class AddQuantityTest extends BaseTest {
	@Override
	public void run() {
		ProductDao productDao = applicationContext.getBean(ProductDao.class);
		println(productDao.addQuantity(1, 10));
	}

	public static void main(String[] args) {
		TestExecutor.run(AddQuantityTest.class);
	}
}