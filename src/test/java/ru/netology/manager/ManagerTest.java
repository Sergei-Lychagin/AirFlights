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

    @BeforeEach
    public void setUp() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fourth);
    }

    @Test
    public void shouldFindAll() {
        Ticket[] actual = manager.findAll("LED", "SVO");
        Ticket[] expected = new Ticket[]{ second, first};
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
        Ticket[] expected = new Ticket[]{second, first, fourth, third};
        Ticket[] actual = new Ticket[]{first, second, third, fourth};
        Arrays.sort(actual);
        assertArrayEquals(expected, actual);
    }

}