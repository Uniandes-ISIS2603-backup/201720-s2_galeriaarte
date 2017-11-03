/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("artistaModule");
    mod.constant("artistasContext", "api/artistas");
    mod.controller('artistaCtrl', ['$scope', '$http', 'artistasContext', '$state',
        function ($scope, $http, artistasContext, $state) {
            $http.get(artistasContext).then(function (response) {
                $scope.artistasRecords = response.data;
            });
            
           if ($state.params.artistaId !== undefined) {
                $http.get(artistasContext + '/' + $state.params.artistaId).then(function (response) {
            
                    $scope.currentArtista = response.data;
                });
            } 
        }
    ]);
}
)(angular);

