package io.tonesniper.rig;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

record SignalChainItem(
    UUID gearId,
    int orderIndex,
    List<KnobSetting> knobSettings
) implements Serializable {
}
