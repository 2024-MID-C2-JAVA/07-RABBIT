package co.com.sofka.cuentaflex.domain.models;

import java.util.*;
import java.util.function.Consumer;

public final class AccountMovementsHistory implements Iterable<AccountMovement> {
    private final Deque<AccountMovement> movements;

    public AccountMovementsHistory() {
        this.movements = new ArrayDeque<>();
    }

    public AccountMovementsHistory(Collection<AccountMovement> initialMovements) {
        this.movements = new ArrayDeque<>(initialMovements);
    }

    public void addMovement(AccountMovement movement) {
        this.movements.addLast(movement);
    }

    public AccountMovement getLastMovement() {
        return this.movements.peekLast();
    }

    public int getMovementCount() {
        return this.movements.size();
    }

    @Override
    public Iterator<AccountMovement> iterator() {
        return this.movements.iterator();
    }

    @Override
    public void forEach(Consumer<? super AccountMovement> action) {
        this.movements.forEach(action);
    }

    @Override
    public Spliterator<AccountMovement> spliterator() {
        return this.movements.spliterator();
    }
}
