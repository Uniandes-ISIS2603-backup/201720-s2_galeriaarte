(function (ng) {
    var mod = ng.module("hojaVidaModule");
    mod.constant("hojasVidaContext", "api/hojasVida");
    mod.controller('hojaVidaNewCtrl', ['$scope', '$http', 'hojasVidaContext', '$state', '$rootScope',
        function ($scope, $http, hojasVidaContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createHojaVida = function () {
                $http.post(hojasVidaContext, {
                    id: $scope.hojaVidaId,
                    trayectoria: $scope.hojaVidaTrayectoria,
                    almaMater: $scope.hojaVidaAlmaMater,
                    nacionalidad: $scope.hojaVidaNacionalidad
                   
                }).then(function (response) {
                    //Hoja created successfully
                    $state.go('hojasVidaList', {hojaVidaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);


