package org.leechiwi.happyseven.files.all.convert;

import lombok.extern.slf4j.Slf4j;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.base.enums.ResultOptions;

import java.io.OutputStream;
import java.util.Objects;
@Slf4j
public abstract class ConvertChainHandler<E> {
    protected ConvertChainHandler<E> next;
    protected OutputStream nextIn;

    public void next(ConvertChainHandler<E> next) {
        this.next = next;
    }

    public  abstract  boolean doHandler(Object in, OutputStream out,E fileConvertType, ResultOptions resultOptions,OptionResult optionResult);

    public abstract Object doHandlerPost(OutputStream outputStream);

    public boolean start(Object in, OutputStream out,E fileConvertType, ResultOptions resultOptions,OptionResult optionResult){
        boolean result = this.doHandler(in, out, fileConvertType, resultOptions, optionResult);
        if(result){
            if(Objects.isNull(this.next)){
                return result;
            }
            if(Objects.isNull(this.nextIn)){
                log.error("current convert chain has next but can not find next  in");
                return false;
            }
            Object nextIn=this.doHandlerPost(this.nextIn);
            result= this.next.start(nextIn,out,fileConvertType,resultOptions,optionResult);
        }
        return result;
    }
    public static class Builder<E> {
        private ConvertChainHandler<E> head;
        private ConvertChainHandler<E> rear;

        public Builder addHandler(ConvertChainHandler<E> handler) {
            if (this.head == null) {
                this.head = handler;
                this.rear = handler;
                return this;
            }
            this.rear.next(handler);
            this.rear=handler;
            return this;
        }
        public ConvertChainHandler<E> build(){
            return this.head;
        }
    }
}
