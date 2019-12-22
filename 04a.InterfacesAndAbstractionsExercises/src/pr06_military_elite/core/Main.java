package pr06_military_elite.core;

import pr06_military_elite.utils.readers.ConsoleReader;
import pr06_military_elite.utils.readers.InputReader;

public class Main {
    public static void main(String[] args) {
        InputReader reader = new ConsoleReader();
        Engine engine = new EngineImpl(reader);

        engine.run();
    }
}
