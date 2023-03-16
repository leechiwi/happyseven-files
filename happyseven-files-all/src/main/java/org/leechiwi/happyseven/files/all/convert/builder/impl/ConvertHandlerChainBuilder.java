package org.leechiwi.happyseven.files.all.convert.builder.impl;

import org.leechiwi.happyseven.files.all.convert.ConvertChainHandler;
import org.leechiwi.happyseven.files.all.convert.builder.ChainBuilder;

import java.util.List;

public class ConvertHandlerChainBuilder<E> implements ChainBuilder<ConvertChainHandler<E>> {
    public ConvertChainHandler<E> build(List<ConvertChainHandler<E>> chainHandlerList) {
        ConvertChainHandler.Builder<E> builder = new ConvertChainHandler.Builder();
        for (ConvertChainHandler<E> convertChainHandler : chainHandlerList) {
            builder = builder.addHandler(convertChainHandler);
        }
        ConvertChainHandler<E> firstHandler = builder.build();
        return firstHandler;
    }
}
