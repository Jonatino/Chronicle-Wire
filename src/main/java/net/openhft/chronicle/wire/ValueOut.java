/*
 * Copyright 2015 Higher Frequency Trading
 *
 * http://www.higherfrequencytrading.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.openhft.chronicle.wire;

import net.openhft.chronicle.bytes.Bytes;
import net.openhft.chronicle.core.Maths;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.function.Consumer;

/**
 * Created by peter.lawrey on 14/01/15.
 */
public interface ValueOut {
    /*
     * data types
     */
    WireOut bool(Boolean flag);

    WireOut text(CharSequence s);

    default WireOut int8(long x) {
        return int8(Maths.toInt8(x));
    }

    WireOut int8(byte i8);

    WireOut bytes(Bytes fromBytes);

    ValueOut writeLength(long remaining);

    WireOut bytes(byte[] fromBytes);

    default WireOut uint8(long x) {
        return uint8checked((byte) Maths.toUInt8(x));
    }

    WireOut uint8checked(int u8);

    default WireOut int16(long x) {
        return int16(Maths.toInt16(x));
    }

    WireOut int16(short i16);

    default WireOut uint16(long x) {
        return uint16checked((short) Maths.toUInt16(x));
    }

    WireOut uint16checked(int u16);

    WireOut utf8(int codepoint);

    default WireOut int32(long x) {
        return int32(Maths.toInt32(x));
    }

    WireOut int32(int i32);

    default WireOut uint32(long x) {
        return uint32checked((int) Maths.toUInt32(x));
    }

    WireOut uint32checked(long u32);

    WireOut int64(long i64);

    WireOut int64array(long capacity);

    WireOut float32(float f);

    WireOut float64(double d);

    WireOut time(LocalTime localTime);

    WireOut zonedDateTime(ZonedDateTime zonedDateTime);

    WireOut date(LocalDate localDate);

    WireOut type(CharSequence typeName);

    WireOut uuid(UUID uuid);

    WireOut int32forBinding(int value);

    WireOut int64forBinding(long readReady);

    WireOut sequence(Consumer<ValueOut> writer);

    WireOut marshallable(WriteMarshallable object);

    boolean isNested();

    WireOut nested(boolean nested);

    default WireOut typedMarshallable(Marshallable object) {
        type(object.getClass().getName());
        return marshallable(object);
    }
}
