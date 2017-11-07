/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("hojaVidaModule");
    mod.constant("hojasVidaContext", "api/hojasVida");
    mod.controller('hojaVidaCtrl', ['$scope', '$http', 'hojasVidaContext', '$state',
        function ($scope, $http, hojasVidaContext, $state) {
            $http.get(hojasVidaContext).then(function (response) {
                $scope.hojasVidaRecords = response.data;
            });
            
           if ($state.params.hojaVidaId !== undefined) {
                $http.get(hojasVidaContext + '/' + $state.params.hojaVidaId).then(function (response) {
            
                    $scope.currentHojaVida = response.data;
                });
            } 
        }
    ]);
}
)(angular);

