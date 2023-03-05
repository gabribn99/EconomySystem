package plugin.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import plugin.entities.BalanceBean;

import java.io.IOException;

public class BalanceAdapter extends TypeAdapter<BalanceBean> {

    @Override
    public void write(JsonWriter writer, BalanceBean balanceBean) throws IOException {
        writer.beginObject();

        writer.name("playerName");
        writer.value(balanceBean.getPlayerName());

        writer.name("amount");
        writer.value(balanceBean.getAmount());

        writer.endObject();
    }

    @Override
    public BalanceBean read(JsonReader reader) throws IOException {
        BalanceBean locationBean = new BalanceBean();
        reader.beginObject();
        String fieldName = null;

        while (reader.hasNext()) {
            JsonToken token = reader.peek();

            if (token.equals(JsonToken.NAME)) {
                fieldName = reader.nextName();
            }

            if ("playerName".equals(fieldName)) {
                token = reader.peek();
                locationBean.setPlayerName(reader.nextString());
            }

            if ("amount".equals(fieldName)) {
                token = reader.peek();
                locationBean.setAmount(reader.nextDouble());
            }
        }
        reader.endObject();
        return locationBean;
    }
}
