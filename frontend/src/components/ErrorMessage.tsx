function ErrorMessage({ message }: { message: string }) {
  return (
    <div className="mt-4 bg-red-50 rounded-xl px-4 py-3 text-center">
      <p className="text-sm text-red-600">{message}</p>
    </div>
  );
}
export default ErrorMessage;