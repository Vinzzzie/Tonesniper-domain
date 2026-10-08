package io.tonesniper.domain.gear;

import java.io.Serializable;

public record KnobSpec (
    String name,
    int min,
    int max,
    int defaultValue
)  implements Serializable {
}
