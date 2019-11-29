package services.implementations;

import entity.Guest;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import services.GuestService;
import services.dto.GuestDTO;
import static org.mockito.Mockito.*;

import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class GuestServiceImplTest {

    GuestService guestService = new GuestServiceImpl();
    GuestDTO guestDTO;
   // @Mock
   // GuestService guestServiceMock;
   // @InjectMocks
   // GuestService guestServiceMock = new GuestServiceImpl();

    @Before
    public void init(){
        guestDTO = new GuestDTO("+375297162984", Arrays.asList(10,10), "Ilya", "Tarasenko",
                "Vitalievich", "1234", Arrays.asList("SPA","swimming pool"),
                     Arrays.asList(123.0,321.0), 20.0);
    }
    @After
    public void clear(){
        guestDTO = null;
    }

    @Test
    public void getId() {
        GuestDTO guest = guestService.getById(10);
        Assert.assertNotNull(guest);
        Assert.assertEquals(this.guestDTO,guest);
    }

/*    @Test
    public void getIdMock(){
        when(guestServiceMock.getById(anyInt())).thenReturn(guestDTO);
        verify(guestServiceMock, times(1)).getById(anyInt());
    }*/

    @Test
    public void getByIdNull() {
        GuestDTO guest = guestService.getById(1);
        Assert.assertNull(guest);
    }

    @Test
    public void createGuest() {
    }
}