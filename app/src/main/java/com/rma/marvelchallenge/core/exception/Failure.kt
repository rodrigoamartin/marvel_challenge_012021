package com.rma.marvelchallenge.core.exception

/**
 * Base Class for handling errors/failures/exceptions.
 * Every feature specific failure should extend [FeatureFailure] class.
 */
sealed class Failure {

    fun withMessage(msg: String): Failure {
        this.message = msg
        this.hasMessage = true
        return this
    }

    open var message: String = ""
    var hasMessage: Boolean = false

    object NetworkConnection : Failure()
    object ServerError : Failure()
    object PageNotFound: Failure()

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure: Failure()
}