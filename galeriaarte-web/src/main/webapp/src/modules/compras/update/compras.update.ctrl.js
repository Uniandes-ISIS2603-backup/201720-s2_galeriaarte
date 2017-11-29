(
        function (ng) {
            var mod = ng.module("compraModule");
            mod.constant("comprasContext", "api/compra");
            mod.constant("pagosContext", "api/pagos");
            mod.controller('compraUpdateCtrl', ['$scope', '$http', 'comprasContext', '$state', 'pagosContext', '$rootScope', '$filter',
                function ($scope, $http, comprasContext, $state, pagosContext, $rootScope, $filter) {
                    $rootScope.edit = true;

                    var idCompra = $state.params.compraId;

                    // Este arreglo guardara los ids de los books asociados y por asociar al autor.
                    var idsPago = [];

                    // Este arreglo mostrará los books una vez esten filtrados visualmente por lo que el autor ya tiene asociado.
                    $scope.allPagosShow = [];

                    //Consulto el autor a editar.
                    $http.get(comprasContext + '/' + idCompra).then(function (response) {
                        var compra = response.data;
                        $scope.valor = compra.valor;
                        $scope.fecha = compra.fecha;
                        $scope.allPagosCompra = compra.pagos;
                        $scope.mergePagos($scope.allPagosCompra);
                    });

                    /*
                     * Esta función añade los ids de los books que ya tiene el autor asociado.
                     * @param {type} books: Son los books que ya tiene asociado el autor.
                     * @returns {undefined}
                     */
                    $scope.mergePagos = function (pagos) {
                        for (var item in pagos) {
                            idsPago.push("" + pagos[item].id);
                        }
                        $scope.getPagos(pagos);
                    };

                    /*
                     * Esta función recibe como param los books que tiene el autor para hacer un filtro visual con todos los books que existen.
                     * @param {type} books
                     * @returns {undefined}
                     */
                    $scope.getPagos = function (pagos) {
                        $http.get(pagosContext).then(function (response) {
                            $scope.Allpagos = response.data;
                            $scope.pagosCompra = pagos;

                            var filteredPagos = $scope.Allpagos.filter(function (Allpagos) {
                                return $scope.pagosCompra.filter(function (pagosCompra) {
                                    return pagosCompra.id == Allpagos.id;
                                }).length == 0
                            });

                            $scope.allPagosShow = filteredPagos;

                        });
                    };


                    //funciones para el drag and drop de HTML5 nativo
                    $scope.allowDrop = function (ev) {
                        ev.preventDefault();
                    };

                    $scope.drag = function (ev) {
                        ev.dataTransfer.setData("text", ev.target.id);
                    };

                    $scope.dropAdd = function (ev) {
                        ev.preventDefault();
                        var data = ev.dataTransfer.getData("text");
                        ev.target.appendChild(document.getElementById(data));
                        //Cuando un book se añade al autor, se almacena su id en el array idsBook
                        idsPago.push("" + data);
                    };

                    $scope.dropDelete = function (ev) {
                        ev.preventDefault();
                        var data = ev.dataTransfer.getData("text");
                        ev.target.appendChild(document.getElementById(data));
                        //Para remover el book que no se va asociar, por eso se usa el splice que quita el id del book en el array idsBook
                        var index = idsPago.indexOf(data);
                        if (index > -1) {
                            idsPago.splice(index, 1);
                        }
                    };

                    $scope.createCompra = function () {
                        /*Se llama a la función newBooks() para buscar cada uno de los ids de los books
                         en el array que tiene todos los books y así saber como queda la lista final de los books asociados al autor.
                         */
                        $scope.newPagos();
                        $http.put(comprasContext + "/" + idCompra, {
//                            id: 0,
                            valor: $scope.valor,
                            fecha: $scope.fecha
                        }).then(function (response) {
                            //Author created successfully
                            $state.go('comprasList', {compraId: response.data.id}, {reload: true});
                        });
                    };

                    $scope.newPagos = function () {
                        $scope.allPagosCompra = [];
                        for (var ite in idsPago) {
                            for (var all in $scope.Allpagos) {
                                if ($scope.Allpagos[all].id === parseInt(idsPago[ite])) {
                                    $scope.allPagosCompra.push($scope.Allpagos[all]);
                                }
                            }
                        }
                    };
                }
            ]);
        }
)(window.angular);