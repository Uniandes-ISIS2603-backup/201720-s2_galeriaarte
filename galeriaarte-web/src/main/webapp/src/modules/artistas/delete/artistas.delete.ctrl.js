(function (ng) {
    var mod = ng.module("artistaModule");
    mod.constant("artistasContext", "api/artistas");
    mod.controller('artistaDeleteCtrl', ['$scope', '$http', 'artistasContext', '$state',
        function ($scope, $http, artistasContext, $state) {
            var idArtista = $state.params.artistaId;
            $scope.deleteArtista = function () {
                $http.delete(artistasContext + '/' + idArtista, {}).then(function (response) {
                    $state.go('artistasList', {artistaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);

