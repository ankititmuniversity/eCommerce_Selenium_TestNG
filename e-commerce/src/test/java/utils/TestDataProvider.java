package utils;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class TestDataProvider {
    String dataPath = System.getProperty("user.dir") + "//testData//login.xlsx";
    Excel_RW excel = new Excel_RW(dataPath);

    @DataProvider(name = "credentials")
    public String[][] getData() throws IOException {

        int ttlRow = excel.getRowCount("Sheet1");
        int ttlCell = excel.getCellCount("Sheet1", 1);
        String[][] userData = new String[ttlRow][ttlCell];
        for (int i = 1; i <= ttlRow; i++) {
            for (int j = 0; j < ttlCell; j++) {
            	userData[i - 1][j] = excel.getCellData("Sheet1", i, j);
            }
        }
        return userData;
    }

    @DataProvider(name = "UserName")
    public String[] getUserName() throws IOException {
        int ttlRow = excel.getRowCount("Sheet1");
//      int ttlCell =excel.getCellCount("Sheet1",1);
        String[] apiData = new String[ttlRow];
        for (int i = 1; i <= ttlRow; i++) {
            apiData[i - 1] = excel.getCellData("Sheet1", i, 1);
        }
        return apiData;
    }
}