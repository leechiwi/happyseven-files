package org.leechiwi.happyseven.files.all.convert.builder;

import org.leechiwi.happyseven.files.all.convert.ConvertChainHandler;

import java.util.List;

public interface ChainBuilder<T> {
    T build(List<T> chainHandlerList);
}
