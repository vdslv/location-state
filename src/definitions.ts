export interface LocationStatePlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
