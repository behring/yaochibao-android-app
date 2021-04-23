package behring.android.yaochibao.foods.android.ui.base;

import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;

public class ObservableViewModel extends BaseViewModel implements Observable {
    private final PropertyChangeRegistry callbacks = new PropertyChangeRegistry();

    @Override
    public void addOnPropertyChangedCallback(
            OnPropertyChangedCallback callback) {
        callbacks.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(
            OnPropertyChangedCallback callback) {
        callbacks.remove(callback);
    }

    protected void notifyChange() {
        callbacks.notifyCallbacks(this, 0, null);
    }

    protected void notifyPropertyChanged(int fieldId) {
        callbacks.notifyCallbacks(this, fieldId, null);
    }
}