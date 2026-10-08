package io.tonesniper.domain.rig;

import io.tonesniper.domain.gear.KnobSpec;

import java.io.Serializable;

record KnobSetting(
    KnobSpec knob,
    int value
) implements Serializable {
}
