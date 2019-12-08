package investment.foundations;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.lang.Exception;
import investment.foundations.Stock;

class TestStockFactory {

	@Test
	void test() {
		String[] values={"BABA","GOOG"};
		try {
			Stock alibaba=StockFactory.getStockFactory().getStock("BABA");
			alibaba.getLastDate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fail("Not yet implemented");
	}

}
