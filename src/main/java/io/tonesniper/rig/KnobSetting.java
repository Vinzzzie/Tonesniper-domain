package io.tonesniper.rig;

import io.tonesniper.gear.KnobSpec;

import java.io.Serializable;

record KnobSetting(
    KnobSpec knob,
    int value
) implements Serializable {
}
