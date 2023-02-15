package org.leechiwi.happyseven.files.all.convert.builder;

import org.leechiwi.happyseven.files.all.convert.ConvertChainHandler;

public interface ChainBuilder<E> {
    ConvertChainHandler<E> build();
}
