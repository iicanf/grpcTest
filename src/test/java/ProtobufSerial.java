import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Timestamp;
import com.iicanf.model.User.UserOuterClass;
import com.iicanf.model.enums.Nation.NationOuterClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author: guozhenhua
 * @create: 2019-05-27 16:08
 **/
public class ProtobufSerial {
    private final static Logger LOGGER = LoggerFactory.getLogger(ProtobufSerial.class);

    @Test
    public void setialTest() {
        UserOuterClass.User user = UserOuterClass.User.newBuilder()
                .setAge(10)
                .setInsertTime(Timestamp.newBuilder().setSeconds(new Date().getTime()).setNanos(0).build())
                .setName("test")
                .setIsDelete(false)
                .setPhone("1300000000")
                .setNation(NationOuterClass.Nation.CHINA)
                .setUpdateTime(Timestamp.newBuilder().setSeconds(new Date().getTime()).setNanos(0).build())
                .build();
        byte[] bytes = user.toByteArray();
        ByteString serial = user.toByteString();
        String string = user.toString();

        LOGGER.info(string);
//        LOGGER.info(serial.toStringUtf8());
//        LOGGER.info("{}", bytes);

        UserOuterClass.User user1 = null;
        try {
            user1 = UserOuterClass.User.parseFrom(bytes);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        LOGGER.info("{},{},{}", user1.toString(), user1.getNation().getValueDescriptor(), String.valueOf(user1.getIsDelete()));
    }

}
