package linal.specs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class CTest {
    private static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws JsonProcessingException {
        List<ISpec> configs = new ArrayList<>();
        configs.add(new SpecDecimal("camera.trans.pos.x"));
        configs.add(new SpecDecimal("camera.trans.pos.y"));
        configs.add(new SpecDecimal("camera.trans.pos.z"));

        configs.add(new SpecString("camera.pos.test", "text", 2, 10));

        configs.add(new SpecBoolean("camera.pos.active"));
        configs.add(new SpecBoolean("camera.rot.active", true));

        configs.add(new SpecDecimal("main.cores", 1, 10, 2));

        String str = mapper.writeValueAsString(configs);

        System.out.println(str);
    }
}
