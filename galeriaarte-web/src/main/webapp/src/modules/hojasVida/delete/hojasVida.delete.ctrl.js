(function (ng) {
    var mod = ng.module("hojaVidaModule");
    mod.constant("hojasVidaContext", "api/hojasVida");
    mod.controller('hojaVidaDeleteCtrl', ['$scope', '$http', 'hojasVidaContext', '$state',
        function ($scope, $http, hojasVidaContext, $state) {
            var idHojaVida = $state.params.hojaVidaId;
            $scope.deleteHojaVida = function () {
                $http.delete(hojasVidaContext + '/' + idHojaVida, {}).then(function (response) {
                    $state.go('hojasVidaList', {hojaVidaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);

