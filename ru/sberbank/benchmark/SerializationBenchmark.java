package ru.sberbank.benchmark;

import com.fasterxml.jackson.dataformat.protobuf.ProtobufMapper;
import com.fasterxml.jackson.dataformat.protobuf.schema.ProtobufSchema;
import com.google.gson.Gson;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import ru.sberbank.jsonparser.Client;
import ru.sberbank.jsonparser.Holding;
import ru.sberbank.jsonparser.Individual;
import ru.sberbank.jsonparser.Owner;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
public class SerializationBenchmark {

    @Benchmark
    public Client gsonBenchmark(Blackhole blackhole) {
        Client client1 = new Holding("Dino", 2983756, 31);
        Gson gson = new Gson();
        String json = gson.toJson(client1);
        Client client2 = gson.fromJson(json, Individual.class);
        return client2;
    }

    @Benchmark
    public Client protobufBenchmark(Blackhole blackhole) throws IOException {
        Client client1 = new Holding("Dino", 2983756, 31);
        ProtobufMapper mapper = new ProtobufMapper();
        ProtobufSchema schema = mapper.generateSchemaFor(Holding.class);
        byte[] bytes = mapper.writer(schema).writeValueAsBytes(client1);
        Client client2 = mapper.readerFor(Holding.class).with(schema).readValue(bytes);
        return client2;
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(SerializationBenchmark.class.getSimpleName())
                .warmupIterations(10)
                .measurementIterations(20)
                .forks(1)
                .timeout(TimeValue.seconds(40))
                .build();

        new Runner(options).run();
    }
}
