import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class StockManagementTests {

    @Test
    public void testCanGetACorrectLocatorCode() {
        ExternalISBNDataService databaseService = new ExternalISBNDataService() {
            @Override
            public Book lookup(String isbn) {
                return null;
            }
        };

        ExternalISBNDataService testWebService = mock(ExternalISBNDataService.class);
        when(testWebService.lookup(anyString()))
                .thenReturn(new Book("0140177396", "Of Mice And Men", "J. Steinbeck"));

        StockManager stockManager = new StockManager();
        stockManager.setWebService(testWebService);
        stockManager.setDatabaseService(databaseService);
        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("7396J4", locatorCode);
    }

    @Test
    public void databaseIsUsedIfDataIsPresent() {
        ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class);
        ExternalISBNDataService webService = mock(ExternalISBNDataService.class);

        when(databaseService.lookup("0140177396"))
                .thenReturn(new Book("0140177396", "abc", "abc"));

        StockManager stockManager = new StockManager();
        stockManager.setWebService(webService);
        stockManager.setDatabaseService(databaseService);

        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);

        verify(databaseService).lookup("0140177396");
        verify(webService, times(0)).lookup(anyString());
    }
    @Test
    public void webserviceIsUsedIfDataIsNotPresentInDatabase() {
        ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class);
        ExternalISBNDataService webService = mock(ExternalISBNDataService.class);
        when(databaseService.lookup("0140177396")).thenReturn(null);
        when(webService.lookup("0140177396"))
                .thenReturn(new Book("0140177396", "abc", "abc"));

        StockManager stockManager = new StockManager();
        stockManager.setWebService(webService);
        stockManager.setDatabaseService(databaseService);

        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);

        verify(databaseService).lookup("0140177396");
        verify(webService).lookup("0140177396");
    }
}
