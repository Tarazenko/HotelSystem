package services.implementations;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import services.RoomService;
import services.dto.RoomDTO;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RoomServiceImplTest {

    @Mock
    private RoomService roomService = new RoomServiceImpl();

    @Captor
    ArgumentCaptor<Integer> integerArgument;

    @Test
    public void getRoomById() {
        when(roomService.getRoomById(anyInt())).thenReturn(new RoomDTO());
        assertNotNull(roomService.getRoomById(1));
        verify(roomService).getRoomById(integerArgument.capture());
        assertEquals("1",integerArgument.getValue().toString());
        verify(roomService, times(1)).getRoomById(1);
    }

    @Test
    public void getRoomByNumber() {
    }

    @Test
    public void getAll() {
    }
}