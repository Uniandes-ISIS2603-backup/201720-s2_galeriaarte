/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("obraModule");
    mod.constant("obrasContext", "api/obras");
    mod.controller('obraCtrl', ['$scope', '$http', 'obrasContext', '$state', '$rootScope',
        function ($scope, $http, obrasContext, $state, $rootScope) {
            $http.get(obrasContext).then(function (response) {
                $scope.obrasRecords = response.data;
            });
            
           if ($state.params.obraId !== undefined) {
                $http.get(obrasContext + '/' + $state.params.obraId).then(function (response) {
                    $scope.comentariosRecords = response.data.comentarios;
                    $scope.currentObra = response.data;
                });
            } 
            $scope.AddFavorito = function () {
                $http.post('api/cliente/' + $rootScope.currentId +'/obras/' + $state.params.obraId,{});
                alert("Hola Mundo Estes es el id: "+ $state.params.obraId);
            };
        }
        
    ]);
}
          
)

(angular);

