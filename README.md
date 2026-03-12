Responda as seguintes perguntas:

Qual foi o speedup1 obtido?
R: 1,9 com 10 threads e 1,76 com 100 threads

Teve algum caso que o speedup foi negativo?
R: Não. Em todos os testes o tempo com threads foi menor que o sequencial.

Repita os testes acima usando Threads virtuais.
Houve diferenças entre Threads de plataforma e Threads virtuais? Se houve, quais foram e explique o porquê das diferenças.
R: Sim, mas nesse caso a diferença é pequena, com 10 threads virtuais o tempo foi 114 ms, um pouco mais lento que as threads de plataforma. Já com 100 threads virtuais o tempo caiu para 76 ms. A diferença foi pequena porque o programa faz principalmente cálculos na CPU, então o tipo de thread não influencia tanto. As threads virtuais são mais leves e funcionam melhor quando há muitas threads, por isso com 100 threads elas ficaram mais rápidas.