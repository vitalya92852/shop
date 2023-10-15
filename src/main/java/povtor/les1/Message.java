package povtor.les1;

import java.util.Objects;

public class Message {

    private final String value;

    public Message(String value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Message message = (Message) obj;
        return value.equals(message.value);
    }

    @Override
    public String toString() {
        return "Message{value='%s'}".formatted(value);
    }

    public String getValue() {
        return value;
    }
}
