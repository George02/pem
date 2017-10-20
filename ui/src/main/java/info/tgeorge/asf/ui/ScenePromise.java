/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.tgeorge.asf.ui;

/**
 *
 * @author george
 */
public class ScenePromise {

    private CallbackVoid resolve;
    private CallbackVoid reject;

    public ScenePromise(PromiseCallback runCallback) {
        runCallback.promise(this::resolve, this::reject);
    }

    public ScenePromise() {
    }

    public void run(PromiseCallback runCallback) {
        runCallback.promise(this::resolve, this::reject);
    }

    public void then(CallbackVoid callback) {
        this.resolve = callback;
    }

    public void error(CallbackVoid callback) {
        this.reject.call();
    }

    private void resolve() {
        if (this.resolve != null) {
            this.resolve.call();
        }
    }

    private void reject() {
        if (this.reject != null) {
            this.reject.call();
        }
    }
}
