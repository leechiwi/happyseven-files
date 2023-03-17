package org.leechiwi.happyseven.files.all;

import java.util.List;

public interface ChainBuilder<T> {
    T build(List<T> chainHandlerList);
}
