function ErrorMessage({ message }: { message: string }) {
  return (
    <div className="mt-4 bg-red-50 rounded-xl px-4 py-3 text-center dark:bg-gray-700">
      <p className="text-sm text-red-700">{message}</p>
    </div>
  );
}
export default ErrorMessage;