$(document).ready(function () {

    const toastLiveExample = document.getElementById('liveToast');
    if (toastLiveExample != null) {
        const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample);
        toastBootstrap.show();
    }

    $('.dropdown-item').click(function () {

        $('#teamNameInput').val(this.text)
        $('#chosenTeamId').val($(this).attr( "chosenteamid"))

    });

});

