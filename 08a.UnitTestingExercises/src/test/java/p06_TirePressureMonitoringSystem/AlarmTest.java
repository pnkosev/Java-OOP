package p06_TirePressureMonitoringSystem;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class AlarmTest {
    private Sensor fakeSensor;
    private Alarm alarm;

    @Before
    public void setup() {
        this.fakeSensor = Mockito.mock(Sensor.class);
        this.alarm = new Alarm(this.fakeSensor);
    }

    @Test
    public void shouldReturnAlarmOffOnInitialization() {
        boolean isAlarmOn = this.alarm.getAlarmOn();

        assertFalse("Alarm was not off upon initialization", isAlarmOn);
    }

    @Test
    public void shouldTurnAlarmOnIfTirePressureBelowMinimum() {
        Mockito.when(this.fakeSensor.popNextPressurePsiValue()).thenReturn(16.9);
        this.alarm.check();
        boolean isAlarmOn = this.alarm.getAlarmOn();

        assertTrue("Alarm was not on upon check with tire pressure below minimum", isAlarmOn);
    }

    @Test
    public void shouldTurnAlarmOnIfTirePressureAboveMaximum() {
        Mockito.when(this.fakeSensor.popNextPressurePsiValue()).thenReturn(21.1);
        this.alarm.check();
        boolean isAlarmOn = this.alarm.getAlarmOn();

        assertTrue("Alarm was not on upon check with tire pressure above maximum", isAlarmOn);
    }

    @Test
    public void shouldTurnAlarmOffIfTirePressureWithinNormalBounds() {
        Mockito.when(this.fakeSensor.popNextPressurePsiValue()).thenReturn(17.0);
        this.alarm.check();
        boolean isAlarmOn = this.alarm.getAlarmOn();

        assertFalse("Tire pressure was not off upon check with tire pressure in the normal bounds", isAlarmOn);
    }
}