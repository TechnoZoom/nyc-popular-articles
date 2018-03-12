package mostpopular.kapil.com.nycmostpopular.ui;

import android.arch.lifecycle.LifecycleRegistry;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mostpopular.kapil.com.nycmostpopular.R;
import mostpopular.kapil.com.nycmostpopular.databinding.ErrorFragmentBinding;



public class ErrorFragment extends Fragment {

    private static final String ERROR_MESSAGE = "Error Message";

    private ErrorFragmentBinding errorFragmentBinding;

    public ErrorFragment() {
    }

    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        errorFragmentBinding = DataBindingUtil
                .inflate(inflater, R.layout.error_fragment, container, false);
        return errorFragmentBinding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle args = getArguments();
        if(args != null) {
            if(args.containsKey(ERROR_MESSAGE) && args.getString(ERROR_MESSAGE) != null) {
                errorFragmentBinding.errorTextView.setText(args.getString(ERROR_MESSAGE));
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public static ErrorFragment create(String errorMessage) {
        ErrorFragment ErrorFragment = new ErrorFragment();
        Bundle args = new Bundle();
        args.putString(ERROR_MESSAGE, errorMessage);
        ErrorFragment.setArguments(args);
        return ErrorFragment;
    }
    
}
