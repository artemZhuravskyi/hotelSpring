package com.example.demo.service;

import com.example.demo.DAO.OrderRepository;
import com.example.demo.DAO.RoomRepository;
import com.example.demo.DTO.ReservationDTO;
import com.example.demo.model.Invoice;
import com.example.demo.model.Order;
import com.example.demo.model.Room;
import com.example.demo.model.User;
import com.example.demo.model.enums.RoomClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static com.example.demo.model.enums.RoomClass.ECONOMY;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @Spy
    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private RoomRepository roomRepository;
    @Mock
    private ReservationDTO reservationDTO;
    @Mock
    private InvoiceService invoiceService;
    @Mock
    private Order order;
    @Mock
    private User user;
    @Mock
    private Invoice invoice;
    @Mock
    private Room room;

    private static final Long ID = 1L;
    private static final RoomClass ROOM_CLASS = ECONOMY;
    private static final String DATE_RANGE = "01-01-2022 - 01-02-2022";
    private static final String[] DATES = {"01/01/2022", "01/02/2022"};
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private Optional<Room> roomOptional;
    private static final int pageNum = 1;
    private static final int pageSize = 1;
    private static final String sorting = "price";

    @Before
    public void setUp() throws Exception {
        when(reservationDTO.getFirstDate()).thenReturn(LocalDate.parse(DATES[0], formatter));
        when(reservationDTO.getLastDate()).thenReturn(LocalDate.parse(DATES[1], formatter));
        when(reservationDTO.getRoomId()).thenReturn(ID);
        roomOptional = Optional.of(room);
        when(roomRepository.findById(ID)).thenReturn(roomOptional);
        when(room.getRoomClass()).thenReturn(ROOM_CLASS);

    }

    @Test
    public void itShouldPayOrder() {

        when(order.getInvoice()).thenReturn(invoice);
        when(orderRepository.findByClientAndId(user, ID)).thenReturn(order);
        doNothing().when(orderService).generateReport(any());

        orderService.payOrder(ID, user);

        verify(orderRepository).findByClientAndId(user, ID);
        verify(invoiceService).createInvoice(order);
        verify(order).getInvoice();
        verify(order).setStatus(Order.Status.PAID);
        verify(orderService).generateReport(order);

    }

    @Test
    public void itShouldFindOrdersByClient() {

        List<Order> expected = List.of(order);
        when(orderRepository.findAllByClient(user)).thenReturn(expected);

        List<Order> actual = orderService.findOrdersByClient(user);

        assertEquals(expected, actual);
    }

    @Test
    public void itShouldOrderRoom() {
        when(roomRepository.countIntersectionDateQuantity(reservationDTO.getRoomId(),
                reservationDTO.getLastDate(), reservationDTO.getFirstDate())).thenReturn(Optional.of(1));
        orderService.orderRoom(reservationDTO, user);

        verify(orderService).createOrder(room, user, reservationDTO);
    }

    @Test
    public void itShouldNotOrderRoom() {
        doReturn(Optional.empty()).when(roomRepository).countIntersectionDateQuantity(reservationDTO.getRoomId(),
                reservationDTO.getLastDate(), reservationDTO.getFirstDate());
        orderService.orderRoom(reservationDTO, user);
        verify(orderService, never()).createOrder(room, user, reservationDTO);
    }

    @Test
    public void itShouldOrderRoomById() {
        when(orderService.splitDateToFirstDateAndLastDate(DATE_RANGE)).thenReturn(DATES);
        doReturn(reservationDTO).when(orderService).createReservationDTO(DATES, ID);

        orderService.orderRoomById(DATE_RANGE, ID, user);

        verify(orderService).orderRoom(reservationDTO, user);

    }

    @Test
    public void itShouldShowAllOrdersByRoomAndLastDayAfter() {

        List<Order> expected = List.of(order);
        doReturn(expected).when(orderRepository).findAllByRoomAndLastDateAfter(room, LocalDate.now());

        List<Order> actual = orderService.showAllOrdersByRoomAndLastDayAfter(ID);

        assertEquals(expected, actual);
    }

    @Test
    public void itShouldFindOrdersWithPaginationAndSort() {
        List<Order> expected = List.of(order);
        doReturn(expected).when(orderRepository).findAllByClient(user, PageRequest.of(pageNum, pageSize).withSort(Sort.by(sorting)));

        List<Order> actual = orderService.findOrdersWithPaginationAndSort(user, pageNum, pageSize, sorting);

        assertEquals(expected, actual);
    }

    @Test
    public void itShouldWithdrawPayment() {
        orderService.withdrawPayment(order);
        verify(orderRepository).delete(order);
    }




}
