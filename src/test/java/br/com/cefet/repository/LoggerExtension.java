package br.com.cefet.repository;

import java.util.logging.Logger;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Store;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

public class LoggerExtension implements ExecutionCondition, TestInstancePostProcessor, BeforeEachCallback, AfterEachCallback {

	 private static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(LoggerExtension.class);

	    @Override
	    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {
	        return ConditionEvaluationResult.enabled("Logger Extension está sempre habilitada");
	    }

	    @Override
	    public void postProcessTestInstance(Object testInstance, ExtensionContext context) {
	        Store store = getStore(context);
	        Logger logger = Logger.getLogger(testInstance.getClass().getName());
	        store.put(NAMESPACE, logger);
	    }

	    @Override
	    public void beforeEach(ExtensionContext context) {
	        getStore(context).getOrComputeIfAbsent(NAMESPACE, key -> Logger.getLogger(context.getRequiredTestClass().getName()), Logger.class);
	    }

	    @Override
	    public void afterEach(ExtensionContext context) {
	        getStore(context).remove(NAMESPACE);
	    }

	    private Store getStore(ExtensionContext context) {
	        return context.getStore(NAMESPACE);
	    }

}
