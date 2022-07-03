package linal.typeconfig;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class CTest {
    private static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws JsonProcessingException {
        List<ConfigType> configs = new ArrayList<>();
        configs.add(new TypeConfigDecimal("camera.pos.x"));
        configs.add(new TypeConfigDecimal("camera.pos.y"));
        configs.add(new TypeConfigDecimal("camera.pos.z"));

        configs.add(new TypeConfigString("camera.pos.test", "text", 2, 10));

        configs.add(new TypeConfigBoolean("camera.pos.active"));
        configs.add(new TypeConfigBoolean("camera.rot.active", true));

        String str = mapper.writeValueAsString(configs);

        System.out.println(str);
    }
}
