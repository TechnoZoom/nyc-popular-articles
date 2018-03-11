package mostpopular.kapil.com.nycmostpopular;

import android.support.v4.app.Fragment;

/**
 * Created by kapilbakshi on 10/03/18.
 */

public class BaseFragment extends Fragment {

    private CustomProgressDialog mProgressDialog;

    public void showProgressDialog(String message) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            dismissProgressDialog();
        }
        if (getActivity() == null) {
            return;
        }
        mProgressDialog = new CustomProgressDialog(getActivity(),message);
        mProgressDialog.setCancelable(false);
        try {
            mProgressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dismissProgressDialog() {
        try {
            if (mProgressDialog != null) {
                mProgressDialog.dismiss();
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

}
