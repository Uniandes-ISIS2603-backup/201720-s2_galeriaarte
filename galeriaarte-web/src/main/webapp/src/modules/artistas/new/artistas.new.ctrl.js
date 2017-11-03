(function (ng) {
    var mod = ng.module("artistaModule");
    mod.constant("artistasContext", "api/artistas");
    mod.controller('artistaNewCtrl', ['$scope', '$http', 'artistasContext', '$state', '$rootScope',
        function ($scope, $http, artistasContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createArtista = function () {
                $http.post(artistasContext, {
                    id: $scope.artistaId,
                    name: $scope.authorName
                }).then(function (response) {
                    //Author created successfully
                    $state.go('artistasList', {artistaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);


