package org.leechiwi.happyseven.files.all.convert;

import org.leechiwi.happyseven.files.all.convert.handlers.ConvertChainHandler;
import org.leechiwi.happyseven.files.all.ChainBuilder;
import org.leechiwi.happyseven.files.all.convert.builder.ConvertHandlerChainBuilder;
import org.leechiwi.happyseven.files.base.File;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.base.enums.ResultOptions;

import java.io.OutputStream;
import java.util.List;

public class ConvertChain<E> implements File<E> {
    private Object in;
    private ResultOptions resultOptions;
    private List<ConvertChainHandler<E>> convertChainHandlerList;

    public ConvertChain(Object in, ResultOptions resultOptions, List<ConvertChainHandler<E>> convertChainHandlerList) {
        this.in = in;
        this.resultOptions = resultOptions;
        this.convertChainHandlerList = convertChainHandlerList;
    }

    @Override
    public boolean convertAll(OutputStream out, E fileConvertType, OptionResult optionResult) {
        ChainBuilder<ConvertChainHandler<E>> convertHandlerChainBuilder = new ConvertHandlerChainBuilder<>();
        ConvertChainHandler<E> firstHandler = convertHandlerChainBuilder.build(this.convertChainHandlerList);
        return firstHandler.start(this.in, out, fileConvertType, this.resultOptions, optionResult);
    }
}
