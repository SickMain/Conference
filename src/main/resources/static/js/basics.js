$(document).ready(function () {

    const toastLiveExample = document.getElementById('liveToast');
    if (toastLiveExample != null) {
        const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample);
        toastBootstrap.show();
    }


});