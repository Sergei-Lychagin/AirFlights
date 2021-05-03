package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.repository.Repository;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {

    private Repository repository = new Repository();
    private Manager manager = new Manager(repository);
    private Ticket first = new Ticket(1, 15000, "LED", "SVO", 120);
    private Ticket second = new Ticket(2, 10000, "LED", "SVO", 500);
    private Ticket third = new Ticket(3, 50000, "VOG", "VKO", 150);
    private Ticket fourth = new Ticket(4, 30000, "GOJ", "IST", 200);
    private Ticket fifth = new Ticket(5, 60000, "LED", "SVO", 100);

    @BeforeEach
    public void setUp() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fourth);
        repository.save(fifth);
    }

    @Test
    public void shouldFindAll() {
        Ticket[] actual = manager.findAll("LED", "SVO");
        Ticket[] expected = new Ticket[]{ second, first, fifth};
        assertArrayEquals(actual, expected);

    }

    @Test
    void shouldNotFindTicket() {
        Ticket[] actual = manager.findAll("VKO", "SVO");
        Ticket[] expected = new Ticket[0];
        assertArrayEquals(actual, expected);

    }


    @Test
    public void shouldSortById() {
        Ticket[] expected = new Ticket[]{second, first, fourth, third, fifth};
        Ticket[] actual = new Ticket[]{first, second, third, fourth, fifth};
        Arrays.sort(actual);
        assertArrayEquals(expected, actual);
    }
    @Test
    void shouldFindTicketByTime(){
        Ticket[]actual = manager.findAll("LED","SVO",new TicketByTimeAscComparator());
        Ticket[]expected = new Ticket[]{fifth, first, second};
        assertArrayEquals(actual,expected);

    }

}