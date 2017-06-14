package com.andx.micro.api.core.execute;

/**
 * Created by andongxu on 17-1-4.
 */
public interface TemplateFactory<I, S, O> {

    ExecuteTemplate<I, S, O> getTemplate();
}
