package com.yangxuan;

import com.codahale.metrics.Clock;
import com.codahale.metrics.SlidingTimeWindowReservoir;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class SlidingTimeWindowReservoirTest {

    private final Clock clock = mock(Clock.class);
    private final SlidingTimeWindowReservoir reservoir = new SlidingTimeWindowReservoir(10, TimeUnit.NANOSECONDS, clock);

    @Test
    public void storesMeasurementsWithDuplicateTicks() throws Exception {
        when(clock.getTick()).thenReturn(20L);

        reservoir.update(1);
        reservoir.update(2);

        long[] values = reservoir.getSnapshot().getValues();
        System.out.println(Arrays.toString(values));
    }

    @Test
    public void boundsMeasurementsToATimeWindow() throws Exception {
        when(clock.getTick()).thenReturn(0L);
        reservoir.update(1);

        when(clock.getTick()).thenReturn(5L);
        reservoir.update(2);

        when(clock.getTick()).thenReturn(10L);
        reservoir.update(3);

        when(clock.getTick()).thenReturn(15L);
        reservoir.update(4);

        when(clock.getTick()).thenReturn(20L);
        reservoir.update(5);

        long[] values = reservoir.getSnapshot().getValues();
        System.out.println(Arrays.toString(values));
    }

}
