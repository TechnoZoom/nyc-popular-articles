package mostpopular.kapil.com.nycmostpopular;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import mostpopular.kapil.com.nycmostpopular.constants.ResourceStatus;
import mostpopular.kapil.com.nycmostpopular.models.Resource;


public abstract class ResourceLiveDataObserver<T> implements Observer {


    @Override
    public void onChanged(@Nullable Object o) {

        Resource resource = (Resource) o;

        switch (resource.status) {

            case ResourceStatus.SUCCESS:
                onSuccessfulFetch(resource);
                break;

            case ResourceStatus.ERROR:
                onErrorFetching(resource);
                break;

            case ResourceStatus.LOADING:
                onResourceLoading(resource);
                break;
        }
    }

    public abstract void onSuccessfulFetch(Resource<T> resource);
    public abstract void onResourceLoading(Resource<T> resource);
    public abstract void onErrorFetching(Resource<T> resource);
}
